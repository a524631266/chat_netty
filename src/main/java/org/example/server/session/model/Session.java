package org.example.server.session.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.codec.model.Serializer;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session implements Serializable {
    public static final long serialVersionUID = 123455666L;
    private String userId;
    private String userName;
//    private String sessionId;
}
