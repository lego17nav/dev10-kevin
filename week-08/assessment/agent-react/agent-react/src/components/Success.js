function Success({messages}) {
    if( messages.length === 0) {
        return null;
    }

    return (
        <div className="alert alert-success">
          Success Messages
          <ul>
            <li>{messages}</li>
          </ul>
        </div>
      );
}

export default Success;