package learn.field_agent.data;

import learn.field_agent.data.mappers.AliasMapper;
import learn.field_agent.models.Alias;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AliasJdbcRepository implements AliasRepository{

    private final JdbcTemplate jdbcTemplate;

    public AliasJdbcRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Alias> findAll() {

        final String sql = "select alias_id, `name`, persona, agent_id from alias;";

        return jdbcTemplate.query(sql, new AliasMapper());
    }

    @Override
    public Alias add(Alias alias){

        final String sql = "insert into alias(`name`, persona, `agent_id`) values(?,?,?);";

        KeyHolder kh = new GeneratedKeyHolder();
        int affectedRows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alias.getName());
            ps.setString(2, alias.getPersona());
            ps.setInt(3, alias.getAgentId());
            return ps;

        }, kh);

        if(affectedRows <= 0) {
            return null;
        }

        alias.setAliasId(kh.getKey().intValue());
        return alias;
    }

    @Override
    public boolean update(Alias alias) {
        final String sql = "update alias set " +
                "`name` = ?, " +
                "persona = ?, " +
                "agent_id = ? " +
                "where alias_id = ?;";

        return jdbcTemplate.update(sql,
                alias.getName(),
                alias.getPersona(),
                alias.getAgentId(),
                alias.getAliasId()) > 0;
    }

    @Override
    public boolean delete(int id) {
        final String sql = "delete from alias where alias_id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
