class JwtService {
    constructor() {
        this.ID_TOKEN_KEY = "authToken";
    }

    getToken() {
        console.log(`[Flowcontrol-Jwt] getToken ${this.ID_TOKEN_KEY} `);
        return localStorage.getItem(this.ID_TOKEN_KEY);
    }

    saveToken(token) {
        console.log(`[Flowcontrol-Jwt] saveToken ${this.ID_TOKEN_KEY} token ${token} `);
        localStorage.setItem(this.ID_TOKEN_KEY, token);
    }

    destroyToken() {
        console.log(`[Flowcontrol-Jwt] destroyToken ${this.ID_TOKEN_KEY} `);

        localStorage.removeItem(this.ID_TOKEN_KEY);
    }

}
export default JwtService;