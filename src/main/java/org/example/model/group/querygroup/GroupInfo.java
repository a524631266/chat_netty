package org.example.model.group.querygroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.server.session.model.StateSession;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupInfo {
    String groupId;
    List<StateSession> users;
}
