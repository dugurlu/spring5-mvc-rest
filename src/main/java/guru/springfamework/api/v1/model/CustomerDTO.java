package guru.springfamework.api.v1.model;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String url;
}
