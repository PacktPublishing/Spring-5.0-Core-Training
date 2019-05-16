package com.packt.spring5.tx;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("businessServiceDeclarativeTx")
public class BusinessServiceDeclarativeTx implements BusinessService {

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void doBusiness() {
        System.out.println("Hello from transaction");
        throw new IllegalStateException("Smth went wrong");
    }
}
