class AuthService {
  constructor() {
    this.adminEmail = "admin@yopmail.local";
    this.adminPassword = "admin";
    this.mainurl = "http://localhost:8081/api/";
  }

  async login(data) {
    if (this.isAdminUser(data)) {
      localStorage.setItem("currentUser", JSON.stringify(data));
      return true;
    }

    try {
      const response = await fetch(this.mainurl + "user-login", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (response.ok) {
        const userData = await response.json();
        localStorage.setItem("currentUser", JSON.stringify(userData));
        return true;
      } else {
        throw new Error("Login failed");
      }
    } catch (error) {
      localStorage.setItem("loginError", error.message);
      return false;
    }
  }

  isAdminUser(data) {
    return data.email === this.adminEmail && data.password === this.adminPassword;
  }
}

export default AuthService;