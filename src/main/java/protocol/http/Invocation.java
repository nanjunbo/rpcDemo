package protocol.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invocation implements Serializable {
    private String interfaceName;
    private String methodName;
    private Class[] paramTypes;
    private Object[] params;

}
