    package com.example.blogApp.payload;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class JwtAuthResponse {
        private String token;
    }
