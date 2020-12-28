package org.pkuse2020grp4.pkusporteventsbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class LoginResponseDTO {
    private String token;
    private int userId;
}
