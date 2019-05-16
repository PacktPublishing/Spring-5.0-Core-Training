package com.packt.spring5.tx;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

@Service("businessServiceProgrammaticTx")
public class BusinessServiceProgrammaticTx implements BusinessService {

    private final TransactionTemplate transactionTemplate;

    public BusinessServiceProgrammaticTx(PlatformTransactionManager platformTransactionManager) {
        this.transactionTemplate = new TransactionTemplate(platformTransactionManager);
        this.transactionTemplate.setTimeout(30);
        this.transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
    }

    @Override
    public void doBusiness() {
        String message = transactionTemplate.execute(status -> {
            try {
                throw new IllegalStateException("Hello from transaction");
            } catch (Exception exc) {
                status.setRollbackOnly();
                return "Nothing happened";
            }
        });
        System.out.println(message);
    }
}
