package top.suyiiyii.sims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResponse {
    String message;

    public static CommonResponse factory(String message) {
        return new CommonResponse(message);
    }
}
