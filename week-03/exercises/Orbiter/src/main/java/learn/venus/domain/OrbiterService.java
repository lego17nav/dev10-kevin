package learn.venus.domain;

import learn.venus.models.Orbiter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class OrbiterService {

    public OrbiterResult add(Orbiter orbiter) {
        OrbiterResult result = validateInputs(orbiter);
        if(!result.isSuccess()) {
            return result;
        } else return result;
    }

    private OrbiterResult validateInputs(Orbiter orbiter) {

        OrbiterResult result = new OrbiterResult();
        if(orbiter == null) {
            result.addErrorMessage("Orbiter cannot be null");
            return result;
        }

        if(orbiter.getName() == null || orbiter.getName().trim().length() == 0) {
            result.addErrorMessage("name is required");
        }

        return result;
    }


}
