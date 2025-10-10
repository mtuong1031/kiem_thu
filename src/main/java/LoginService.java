public class LoginService {
    private int failedAttempts = 0;
    private long lockUntil = 0;

    private final String VALID_USERNAME = "user123";
    private final String VALID_PASSWORD = "Password@123";

    public String login(String username, String password) {
        long now = System.currentTimeMillis();

        if (now < lockUntil) {
            return "Tai khoan dang bi khoa. Vui long thu lai sau.";
        }

        if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
            failedAttempts = 0;
            return "Dang nhap thanh cong";
        } else {
            failedAttempts++;
            if (failedAttempts >= 3) {
                lockUntil = now + 5 * 60 * 1000;
                failedAttempts = 0;
                return "Sai thong tin dang nhap. Tai khoan bi khoa 5 phut.";
            }
            return "Sai thong tin dang nhap";
        }
    }

    public String Register(String username, String password) {
        if (username == null || username.length() < 6 || username.length() > 20) {
            return "Gia tri Username khong hop le";
        }
        String passwordError = getPasswordError(password);
        if (passwordError != null) {
            return passwordError;
        }
        return "Dang ky thanh cong";
    }

    private String getPasswordError(String password) {
        if (password == null || password.length() < 8 || password.length() > 20) {
            return "Password khong du do dai";
        }
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        if (!hasUpper) {
            return "Password thieu chu in hoa";
        }
        if (!hasDigit) {
            return "Password thieu chu so";
        }
        if (!hasSpecial) {
            return "Password thieu ky tu dac biet";
        }
        return null;
    }

}