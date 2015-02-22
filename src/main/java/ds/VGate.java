package ds;

/**
 * Created by Ashish on 2/21/2015.
 */
public class VGate {
    private String id;


    public VGate(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        VGate other = (VGate) obj;

        if (id != null ? !id.equals(other.id) : other.id != null) return false;
        return true;
    }

    @Override
    public String toString() {
        return id;
    }
}