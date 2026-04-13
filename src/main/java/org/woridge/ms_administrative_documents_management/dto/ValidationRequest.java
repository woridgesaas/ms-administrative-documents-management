
package org.woridge.ms_administrative_documents_management.dto;

import jakarta.validation.constraints.NotNull;
import org.woridge.ms_administrative_documents_management.enumeration.ValidationAction;

public record ValidationRequest(

        @NotNull(message = "Action is required")
        ValidationAction action  // APPROVE ou REJECT
) {}