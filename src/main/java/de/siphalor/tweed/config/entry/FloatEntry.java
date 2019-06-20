package de.siphalor.tweed.config.entry;

import de.siphalor.tweed.data.DataObject;
import de.siphalor.tweed.data.DataValue;
import net.minecraft.util.PacketByteBuf;

public class FloatEntry extends AbstractValueEntry<Float, FloatEntry> {
	public FloatEntry(Float defaultValue) {
		super(defaultValue);
	}

	@Override
	public Float readValue(DataValue dataValue) {
		return dataValue.asFloat();
	}

	@Override
	public Float readValue(PacketByteBuf buf) {
		return buf.readFloat();
	}

	@Override
	public void writeValue(DataObject parent, String name, Float value) {
		parent.set(name, value);
	}

	@Override
	public void writeValue(Float value, PacketByteBuf buf) {
		buf.writeFloat(value);
	}
}
