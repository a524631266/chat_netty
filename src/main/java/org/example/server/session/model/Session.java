package org.example.server.session.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Session implements Serializable {
    public static final long serialVersionUID = 123455666L;
    private String userId;
    private String userName;
//    private String sessionId;
}
