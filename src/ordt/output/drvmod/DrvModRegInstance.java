package ordt.output.drvmod;

import java.util.ArrayList;
import java.util.List;

import ordt.parameters.ExtParameters;

public class DrvModRegInstance extends DrvModBaseInstance {
	
	private int width = ExtParameters.getMinDataSize();
	private List<DrvModField> fields = new ArrayList<DrvModField>();
	
	DrvModRegInstance(String name, int mapId, int width, long addressOffset, int reps, long addressStride) {
		super(name, mapId, addressOffset, reps, addressStride);
		this.width=width;
	}

	public List<DrvModField> getFields() {
		return fields;
	}

	public void addField(String name, int lowIndex, int width, boolean readable, boolean writeable) {
		this.fields.add(new DrvModField(name, lowIndex, width, readable, writeable));
	}
	
	public int getWidth() {
		return width;
	}

	public class DrvModField {
		public String name;
		public int lowIndex;
		public int width;
		public boolean readable;
		public boolean writeable;
		
		private DrvModField(String name, int lowIndex, int width, boolean readable, boolean writeable) {
			super();
			this.name = name;
			this.lowIndex = lowIndex;
			this.width = width;
			this.readable = readable;
			this.writeable = writeable;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + lowIndex;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + (readable ? 1231 : 1237);
			result = prime * result + width;
			result = prime * result + (writeable ? 1231 : 1237);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			DrvModField other = (DrvModField) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (lowIndex != other.lowIndex)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (readable != other.readable)
				return false;
			if (width != other.width)
				return false;
			if (writeable != other.writeable)
				return false;
			return true;
		}

		private DrvModRegInstance getOuterType() {
			return DrvModRegInstance.this;
		}
		
	}

	@Override
	public int hashCode() { 
		return hashCode(false, false);  // do not include address info, includeChildRegsets has no effect for a reg
	}
	
	@Override
	public int hashCode(boolean includeAddrInfo, boolean includeChildRegsets) {
		final int prime = 31;
		int result = super.hashCode(includeAddrInfo);  
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return equals(obj, false, false);  // do not include address info, includeChildRegsets has no effect for a reg
	}

	@Override
	public boolean equals(Object obj, boolean includeAddrInfo, boolean includeChildRegsets) {
		if (this == obj)
			return true;
		if (!super.equals(obj, includeAddrInfo)) 
			return false;
		if (getClass() != obj.getClass())
			return false;
		DrvModRegInstance other = (DrvModRegInstance) obj;
		if (fields == null) {
			if (other.fields != null)
				return false;
		} else if (!fields.equals(other.fields))
			return false;
		if (width != other.width)
			return false;
		return true;
	}

}