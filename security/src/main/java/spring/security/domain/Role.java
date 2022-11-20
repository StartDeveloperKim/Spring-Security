package spring.security.domain;

public enum Role {

    ROLE_USER {
        @Override
        public String toString() {
            return "ROLE_USER";
        }
    },
    ROLE_ADMIN {
        @Override
        public String toString() {
            return "ROLE_ADMIN";
        }
    },
    ROLE_MANAGER {
        @Override
        public String toString() {
            return "ROLE_MANAGER";
        }
    }
}
