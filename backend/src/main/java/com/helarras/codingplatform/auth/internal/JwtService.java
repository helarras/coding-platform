package com.helarras.codingplatform.auth.internal;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String generateToken() {
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6ImhhbXphQGdtYWlsLmNvbSIsImlhdCI6MTc4NDgxMTk4NywiZXhwIjoxNzg0ODQxOTg3fQ.9XnGdwF4bsIxcVZel2Iz51yYCNMzQZKbvbpBGfsuSVk";
    }
}
