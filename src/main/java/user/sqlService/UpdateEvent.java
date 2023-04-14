package user.sqlService;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class UpdateEvent {

    private Map<String,String> event;

    public UpdateEvent(){
        event = new HashMap<>();
    }


}
