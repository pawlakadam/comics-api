package pl.pawlak.comicsbox.infrastructure.comics.common;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import java.util.UUID;

/**
 * Date: 10.01.19
 * Time: 14:11
 *
 * @author tfert
 */
public class UuidBasicType extends AbstractSingleColumnStandardBasicType<UUID> {

    public static final UuidBasicType INSTANCE = new UuidBasicType();

    public UuidBasicType() {
        super(VarcharTypeDescriptor.INSTANCE, UuidDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "CustomUuid";
    }
}
