package org.pkuse2020grp4.pkusporteventsbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ApplyFormDTO {
    private Integer applyId;

    private int userId;

    private int permission;

    private Date applyDate;
}
