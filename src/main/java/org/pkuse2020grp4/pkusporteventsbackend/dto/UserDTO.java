package org.pkuse2020grp4.pkusporteventsbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Tag;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class UserDTO {
    private int userId;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    private int permission;

    private List<Tag> interestTags;
}
