package co.aurasphere.facebot.model.base;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

/**
 * An object that contains a Facebook's user ID.
 *
 * @author Donato
 * @date 31/lug/2016
 */
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The Facebook's user ID.
     */
    @NotEmpty
    private String id;

    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
