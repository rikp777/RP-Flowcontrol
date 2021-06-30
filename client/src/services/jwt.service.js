class JwtService {
    constructor() {
        this.debug = false;
        this.AuthTokenKey = "authToken";
        this.AuthRefreshTokenKey = "AuthRefreshToken";
    }

    getAuthToken() {
        if(this.debug) console.log(`[Flowcontrol-Jwt] getToken ${this.AuthTokenKey} `);
        return localStorage.getItem(this.AuthTokenKey);
    }

    saveAuthToken(token) {
        if(this.debug) console.log(`[Flowcontrol-Jwt] saveToken ${this.AuthTokenKey} token ${token} `);
        localStorage.setItem(this.AuthTokenKey, token);
    }

    destroyAuthToken() {
        if(this.debug) console.log(`[Flowcontrol-Jwt] destroyToken ${this.AuthTokenKey} `);
        localStorage.removeItem(this.AuthTokenKey);
    }

    getRefreshToken() {
        if(this.debug) console.log(`[Flowcontrol-Jwt] getToken ${this.AuthRefreshTokenKey} `);
        return localStorage.getItem(this.AuthRefreshTokenKey);
    }

    saveRefreshToken(token) {
        if(this.debug) console.log(`[Flowcontrol-Jwt] saveToken ${this.AuthRefreshTokenKey} token ${token} `);
        localStorage.setItem(this.AuthRefreshTokenKey, token);
    }

    destroyRefreshToken() {
        if(this.debug) console.log(`[Flowcontrol-Jwt] destroyToken ${this.AuthRefreshTokenKey} `);
        localStorage.removeItem(this.AuthRefreshTokenKey);
    }

    destroyAllTokens(){
        localStorage.removeItem(this.AuthTokenKey);
        localStorage.removeItem(this.AuthRefreshTokenKey);
    }
}
export default JwtService;