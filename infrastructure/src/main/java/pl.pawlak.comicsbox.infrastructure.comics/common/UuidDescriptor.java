package pl.pawlak.comicsbox.infrastructure.comics.common;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.ImmutableMutabilityPlan;

import java.util.UUID;

/**
 * Date: 10.01.19
 * Time: 14:15
 *
 * @author tfert
 */
public class UuidDescriptor extends AbstractTypeDescriptor<UUID> {


    public static final UuidDescriptor INSTANCE = new UuidDescriptor();

    public UuidDescriptor() {
        super(UUID.class, ImmutableMutabilityPlan.INSTANCE);
    }

    @Override
    public UUID fromString(String string) {
        return UUID.fromString(string);
    }

    @Override
    public <X> X unwrap(UUID value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }

        if (String.class.isAssignableFrom(type)) {
            String serializedUuid = value.toString();
            return (X) serializedUuid;
        }


        throw unknownUnwrap(type);
    }


    @Override
    public <X> UUID wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        
        if (String.class.isInstance(value)) {
            String castedString = (String) value;
            return UUID.fromString(castedString);
        }

        throw unknownWrap(value.getClass());
    }
}
