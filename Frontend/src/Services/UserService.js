class UserService {
  constructor() {
    this.adminEmail = "admin@localhost.local";
    this.adminPassword = "admin";
    this.mainurl = "http://localhost:8081/api/";
  }

  async registerUser(userData) {
    const response = await this.postRequest("user-register", userData);
    console.log(response);
  }

  async getAllUsers() {
    const adminCredentials = {
      email: this.adminEmail,
      password: this.adminPassword,
    };
    const response = await this.postRequest("admin-login", adminCredentials);
    return await response.json();
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem("currentUser"));
  }

  is_loggedin() {
    return this.getCurrentUser() !== null;
  }

  is_admin() {
    const currentUser = this.getCurrentUser();
    return currentUser && currentUser.email === this.adminEmail;
  }

  logout() {
    localStorage.removeItem("currentUser");
  }

  async getAllEmails() {
    const response = await this.getRequest("all-email");
    return await response.json();
  }

  async postRequest(endpoint, data) {
    return await fetch(this.mainurl + endpoint, {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json",
      },
    });
  }

  async getRequest(endpoint) {
    return await fetch(this.mainurl + endpoint);
  }
}

export default UserService;