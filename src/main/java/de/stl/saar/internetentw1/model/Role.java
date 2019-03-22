package de.stl.saar.internetentw1.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Klasse, die einen Benutzer Rolle beschreibt.
 *
 * @see User
 */
@RequiredArgsConstructor
public enum Role {

    ADMIN("Administrator"),
    USER("Benutzer");

    @Getter
    private final String value;
}
