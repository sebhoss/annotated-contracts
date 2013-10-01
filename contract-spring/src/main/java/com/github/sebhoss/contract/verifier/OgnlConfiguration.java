package com.github.sebhoss.contract.verifier;

import com.github.sebhoss.common.annotation.CompilerWarnings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for a OGNL based {@link ContractContextFactory}.
 */
@Configuration
public class OgnlConfiguration {

    /**
     * @return A OGNL based {@link ContractContextFactory}.
     */
    @Bean
    @SuppressWarnings(CompilerWarnings.STATIC_METHOD)
    public ContractContextFactory contractContextFactory() {
        return new OgnlBasedContractContextFactory();
    }

}
