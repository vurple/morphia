package org.mongodb.morphia.query.validation;

import java.util.List;

import static java.lang.String.format;

/**
 * This is a fall-through validator that looks at the type and at the class of the value and figures out if they're similar enough to be
 * used to query.
 */
public final class DefaultTypeValidator extends TypeValidator {
    private static final DefaultTypeValidator INSTANCE = new DefaultTypeValidator();

    private DefaultTypeValidator() {
    }

    @Override
    protected void validate(final Class<?> type, final Object value, final List<ValidationFailure> validationFailures) {
        if (!(value.getClass().isAssignableFrom(type)
              || value.getClass().getSimpleName().equalsIgnoreCase(type.getSimpleName()))) {
            validationFailures.add(new ValidationFailure(format("Type %s may not be queryable with value '%s' with class %s", type,
                                                                value, value.getClass())));
        }
    }

    /**
     * Always returns true, applies to all types
     * @param type the type to be validated
     * @return true.  Always.
     */
    protected boolean appliesTo(final Class<?> type) {
        return true;
    }

    /**
     * Get the instance.
     *
     * @return the Singleton instance of this validator
     */
    public static DefaultTypeValidator getInstance() {
        return INSTANCE;
    }
}
