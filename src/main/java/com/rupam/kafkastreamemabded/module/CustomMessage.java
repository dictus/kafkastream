package com.rupam.kafkastreamemabded.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CustomMessage {

    private String dream;
    private boolean slept;
}
