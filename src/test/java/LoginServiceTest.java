import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        loginService = new LoginService();
    }

    //KIỂM THỬ BIÊN ĐĂNG KÝ
    @Test
    @DisplayName("DK01")
    void DK01() {
        String result = loginService.Register("abcd", "Password@123");
        assertEquals("Gia tri Username khong hop le", result);
    }

    @Test
    @DisplayName("DK02")
    void DK02() {
        String result = loginService.Register("abcde", "Password@123");
        assertEquals("Gia tri Username khong hop le", result);
    }

    @Test
    @DisplayName("DK03")
    void DK03() {
        String result = loginService.Register("abcdef", "Password@123");
        assertEquals("Dang ky thanh cong", result);
    }

    @Test
    @DisplayName("DK04")
    void DK04() {
        String result = loginService.Register("abcdefghijklmno", "Password@123");
        assertEquals("Dang ky thanh cong", result);
    }

    @Test
    @DisplayName("DK05")
    void DK05() {
        String result = loginService.Register("abcdefghijklmnop", "Password@123");
        assertEquals("Dang ky thanh cong", result);
    }

    @Test
    @DisplayName("DK06")
    void DK06() {
        String result = loginService.Register("validUser", "Pass@01");
        assertEquals("Password khong du do dai", result);
    }

    @Test
    @DisplayName("DK07")
    void DK07() {
        String result = loginService.Register("validUser", "Pass@012");
        assertEquals("Dang ky thanh cong", result);
    }

    @Test
    @DisplayName("DK08")
    void DK08() {
        String result = loginService.Register("validUser", "Pass@0123");
        assertEquals("Dang ky thanh cong", result);
    }

    @Test
    @DisplayName("DK09")
    void DK09() {
        String result = loginService.Register("validUser", "Pass@123456789012345");
        assertEquals("Dang ky thanh cong", result);
    }

    @Test
    @DisplayName("DK10")
    void DK10() {
        String result = loginService.Register("validUser", "Pass@1234567890123456");
        assertEquals("Password khong du do dai", result);
    }

    //KIỂM THỬ BIÊN ĐĂNG NHẬP
    @Test
    @DisplayName("DN1")
    void DN1() {
        String result = loginService.login("user123", "Password@123");
        assertEquals("Dang nhap thanh cong", result);
    }

    @Test
    @DisplayName("DN2")
    void DN2() {
        String result = loginService.login("user123", "password@123");
        assertEquals("Sai thong tin dang nhap", result);
    }

    @Test
    @DisplayName("DN3")
    void DN3() {
        String result = loginService.login("Username", "Password@123");
        assertEquals("Sai thong tin dang nhap", result);
    }

    @Test
    @DisplayName("DN4")
    void DN4() {
        loginService.login("wrong", "wrong");
        loginService.login("wrong", "wrong");
        String result = loginService.login("wrong", "wrong");
        assertEquals("Sai thong tin dang nhap. Tai khoan bi khoa 5 phut.", result);
    }

    @Test
    @DisplayName("DN5")
    void DN5() {
        loginService.login("wrong", "wrong");
        loginService.login("wrong", "wrong");
        loginService.login("wrong", "wrong");
        
        String result = loginService.login("user123", "Password@123");
        assertEquals("Tai khoan dang bi khoa. Vui long thu lai sau.", result);
    }


    //KIỂM THỬ BẢNG QUYẾT ĐỊNH CHO HÀM ĐĂNG KÝ (R1-R6)
    @Test
    @DisplayName("TC-R1")
    void TC_R1() {
        String result = loginService.Register("usr", "Password@123");
        assertEquals("Gia tri Username khong hop le", result);
    }

    @Test
    @DisplayName("TC-R2")
    void TC_R2() {
        String result = loginService.Register("user123", "Pass@1");
        assertEquals("Password khong du do dai", result);
    }

    @Test
    @DisplayName("TC-R3")
    void TC_R3() {
        String result = loginService.Register("user123", "password@123");
        assertEquals("Password thieu chu in hoa", result);
    }

    @Test
    @DisplayName("TC-R4")
    void TC_R4() {
        String result = loginService.Register("user123", "Password@");
        assertEquals("Password thieu chu so", result);
    }

    @Test
    @DisplayName("TC-R5")
    void TC_R5() {
        String result = loginService.Register("user123", "Password123");
        assertEquals("Password thieu ky tu dac biet", result);
    }

    @Test
    @DisplayName("TC-R6 - Username và Password hợp lệ")
    void TC_R6() {
        String result = loginService.Register("user123", "Password@123");
        assertEquals("Dang ky thanh cong", result);
    }



    //KIỂM THỬ VỚI ĐỘ PHỦ C2 CHO HÀM LOGIN
    @Test
    @DisplayName("C2.1-TC1 - P1: 0→1→2(T)→3")
    void TC1() {
        // Setup: Khóa tài khoản bằng cách đăng nhập sai 3 lần
        loginService.login("user123", "wrongpass");
        loginService.login("user123", "wrongpass");
        loginService.login("user123", "wrongpass");
        
        // Test: Thử đăng nhập với thông tin đúng khi tài khoản bị khóa
        String result = loginService.login("user123", "Password@123");
        assertEquals("Tai khoan dang bi khoa. Vui long thu lai sau.", result);
    }

    @Test
    @DisplayName("C2.1-TC2 - P2: 0→1→2(F)→4(F)→7→8(F)→11")
    void TC2() {
        String result = loginService.login("abc", "xyz");
        assertEquals("Sai thong tin dang nhap", result);
    }

    @Test
    @DisplayName("C2.1-TC3 - P3: TC3\t0→1→2(F)→4(T)→5(F)→7→8(F)→11")
    void TC3() {
        loginService.login("abc", "xyz");
        
        String result = loginService.login("user123", "123");
        assertEquals("Sai thong tin dang nhap", result);
    }

    @Test
    @DisplayName("C2.1-TC4 - P4: 0→1→2(F)→4(T)→5(T)→6")
    void TC4() {
        loginService.login("abc", "xyz");
        loginService.login("user123", "123");

        String result = loginService.login("user123", "Password@123");
        assertEquals("Dang nhap thanh cong", result);
    }

    @Test
    @DisplayName("C2.1-TC5 - P5: 0→1→2(F)→4(T)→5(F)→7→8(T)→9→10")
    void TC5() {
        loginService.login("abc", "xyz");
        loginService.login("user123", "123");
        
        String result = loginService.login("user123", "123");
        assertEquals("Sai thong tin dang nhap. Tai khoan bi khoa 5 phut.", result);
    }

    //KIỂM THỬ VỚI ĐỘ PHỦ C2 CHO HÀM REGISTER
    @Test
    @DisplayName("C2.2-TC1 - P1: 0→1(T)→4")
    void TC1_Register() {
        String result = loginService.Register(null, "Abc123!");
        assertEquals("Gia tri Username khong hop le", result);
    }

    @Test
    @DisplayName("C2.2-TC2 - P2: 0→1(F)→2(T)→4")
    void TC2_Register() {
        String result = loginService.Register("abc", "Abc123!");
        assertEquals("Gia tri Username khong hop le", result);
    }

    @Test
    @DisplayName("C2.2-TC3 - P3: 0→1(F)→2(F)→3(T)→4")
    void TC3_Register() {
        String result = loginService.Register("abcdefghijklmnopqrstu", "Abc123!");
        assertEquals("Gia tri Username khong hop le", result);
    }

    @Test
    @DisplayName("C2.2-TC4 - P4: 0→1(F)→2(F)→3(F)→5→6(T)→7")
    void TC4_Register() {
        String result = loginService.Register("abcxyz", "abc123");
        assertEquals("Password khong du do dai", result);
    }

    @Test
    @DisplayName("C2.2-TC5 - P5: 0→1(F)→2(F)→3(F)→5→6(F)→8")
    void TC5_Register() {
        String result = loginService.Register("abcxyz", "Abc1234!");
        assertEquals("Dang ky thanh cong", result);
    }

    // Test với độ phủ All-uses
    @Test
    @DisplayName("AU-1")
    void AU1() {
        String result = loginService.Register("", "Password@123");
        assertEquals("Gia tri Username khong hop le", result);
    }

    @Test
    @DisplayName("AU-2")
    void AU2() {
        String result = loginService.Register("abcd", "Password@123");
        assertEquals("Gia tri Username khong hop le", result);
    }

    @Test
    @DisplayName("AU-3")
    void AU3() {
        String result = loginService.Register("abcdefghijklmnovbfđfdfsfdsfdsdf", "Password@123");
        assertEquals("Gia tri Username khong hop le", result);
    }

    @Test
    @DisplayName("AU-4")
    void AU4() {
        String result = loginService.Register("validUser", "Pass@1");
        assertEquals("Password khong du do dai", result);
    }

    @Test
    @DisplayName("AU-5")
    void AU5() {
        String result = loginService.Register("validUser", "Password@123");
        assertEquals("Dang ky thanh cong", result);
    }
}