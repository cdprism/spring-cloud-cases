package org.shancm.serviceprovidercore.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author by shancm
 * @description ProviderProperty
 * @since 2026-02-21 17:19
 */
@Data
@Component
@ConfigurationProperties(prefix = "provider")
public class ProviderProperty {

    String dateTime;

}
