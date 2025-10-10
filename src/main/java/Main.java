public class Main {
    public static void main(String[] args) {
        LoginService loginService = new LoginService();
        System.out.println(loginService.login("user123", "Password@123"));
    }
}