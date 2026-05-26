package com.cart.backend.Configuration;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DbInit {

    private final JdbcTemplate jdbcTemplate;

    public DbInit(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initTriggers() {
        jdbcTemplate.execute("DROP TRIGGER IF EXISTS after_claim_update");
        jdbcTemplate.execute(
            "CREATE TRIGGER after_claim_update AFTER UPDATE ON claim FOR EACH ROW BEGIN " +
            "IF NEW.reviewStatus = '通过' THEN " +
            "IF NEW.type = '违约申请' THEN " +
            "UPDATE client SET status = '已违约' WHERE id = NEW.clientId; " +
            "ELSEIF NEW.type = '重生申请' THEN " +
            "UPDATE client SET status = '未违约' WHERE id = NEW.clientId; " +
            "END IF; END IF; END"
        );
    }
}
