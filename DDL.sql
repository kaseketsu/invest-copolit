CREATE TABLE sys_user
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_account    VARCHAR(64)  NOT NULL UNIQUE,
    password        VARCHAR(255) NOT NULL,
    nickname        VARCHAR(64),
    email           VARCHAR(128),
    phone           VARCHAR(32),
    status          VARCHAR(32)  NOT NULL DEFAULT 'active',
    last_login_time DATETIME(3),
    last_login_ip   VARCHAR(64),
    remark          VARCHAR(500),
    is_deleted      TINYINT      NOT NULL DEFAULT 0,
    created_at      DATETIME(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    updated_at      DATETIME(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    creator         VARCHAR(64)  NOT NULL DEFAULT 'SYS',
    modifier        VARCHAR(64)  NOT NULL DEFAULT 'SYS'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
    COMMENT = '系统用户表';

CREATE TABLE sys_role
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_code       VARCHAR(64)  NOT NULL UNIQUE,
    role_name       VARCHAR(128) NOT NULL,
    role_type       VARCHAR(32)  NOT NULL DEFAULT 'business' COMMENT 'business / system',
    status          VARCHAR(32)  NOT NULL DEFAULT 'active',
    is_builtin      TINYINT      NOT NULL DEFAULT 0 COMMENT '是否内置角色',
    remark          VARCHAR(500),
    is_deleted      TINYINT      NOT NULL DEFAULT 0,
    created_at      DATETIME(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    updated_at      DATETIME(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    creator         VARCHAR(64)  NOT NULL DEFAULT 'SYS',
    modifier        VARCHAR(64)  NOT NULL DEFAULT 'SYS'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
    COMMENT = '系统角色表';

CREATE TABLE sys_permission
(
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    parent_id           BIGINT       NOT NULL DEFAULT 0,
    permission_code     VARCHAR(128) NOT NULL UNIQUE,
    permission_name     VARCHAR(128) NOT NULL,
    permission_type     VARCHAR(32)  NOT NULL,
    resource_path       VARCHAR(255),
    component           VARCHAR(255),
    http_method         VARCHAR(16),
    api_path            VARCHAR(255),
    icon                VARCHAR(128),
    sort                INT          NOT NULL DEFAULT 0,
    visible             TINYINT      NOT NULL DEFAULT 1,
    status              VARCHAR(32)  NOT NULL DEFAULT 'active',
    is_builtin          TINYINT      NOT NULL DEFAULT 0,
    remark              VARCHAR(500),
    is_deleted          TINYINT      NOT NULL DEFAULT 0,
    created_at          DATETIME(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    updated_at          DATETIME(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    creator             VARCHAR(64)  NOT NULL DEFAULT 'SYS',
    modifier            VARCHAR(64)  NOT NULL DEFAULT 'SYS',
    CONSTRAINT chk_sys_permission_type CHECK (permission_type IN ('CATALOG', 'MENU', 'BUTTON', 'API', 'DATA'))
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
    COMMENT = '系统权限表';

CREATE TABLE sys_user_role
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id         BIGINT       NOT NULL,
    role_id         BIGINT       NOT NULL,
    is_deleted      TINYINT      NOT NULL DEFAULT 0,
    created_at      DATETIME(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    updated_at      DATETIME(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    creator         VARCHAR(64)  NOT NULL DEFAULT 'SYS',
    modifier        VARCHAR(64)  NOT NULL DEFAULT 'SYS',
    UNIQUE KEY uk_sys_user_role_user_role (user_id, role_id),
    KEY idx_sys_user_role_user_id (user_id),
    KEY idx_sys_user_role_role_id (role_id),
    CONSTRAINT fk_sys_user_role_user FOREIGN KEY (user_id) REFERENCES sys_user (id),
    CONSTRAINT fk_sys_user_role_role FOREIGN KEY (role_id) REFERENCES sys_role (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
    COMMENT = '用户角色关联表';

CREATE TABLE sys_role_permission
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_id         BIGINT       NOT NULL,
    permission_id   BIGINT       NOT NULL,
    is_deleted      TINYINT      NOT NULL DEFAULT 0,
    created_at      DATETIME(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    updated_at      DATETIME(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    creator         VARCHAR(64)  NOT NULL DEFAULT 'SYS',
    modifier        VARCHAR(64)  NOT NULL DEFAULT 'SYS',
    UNIQUE KEY uk_sys_role_permission_role_perm (role_id, permission_id),
    KEY idx_sys_role_permission_role_id (role_id),
    KEY idx_sys_role_permission_permission_id (permission_id),
    CONSTRAINT fk_sys_role_permission_role FOREIGN KEY (role_id) REFERENCES sys_role (id),
    CONSTRAINT fk_sys_role_permission_permission FOREIGN KEY (permission_id) REFERENCES sys_permission (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
    COMMENT = '角色权限关联表';