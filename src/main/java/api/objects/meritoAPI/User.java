package api.objects.meritoAPI;

public class User {
    private String _userName;
    private String _password;

    public User() {
        _userName = "";
        _password = "";
    }

    public User(String username, String password) {
        _userName = username;
        _password = password;
    }

    public User(User objUser) {
        _userName = objUser._userName;
        _password = objUser._password;
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

}
