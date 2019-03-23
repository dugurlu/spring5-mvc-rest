package guru.springfamework.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    @ApiModelProperty(value = "Customer's First Name", required = true)
    private String firstname;
    @ApiModelProperty(value = "Customer's Last Name", required = true)
    private String lastname;
    private String url;
}
