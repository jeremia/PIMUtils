/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jeremiaMorling.utils.pim;

import java.util.Date;
import javax.microedition.pim.PIMItem;
import jeremiaMorling.utils.vector.IClonable;
import jeremiaMorling.utils.vector.ListItem;

/**
 *
 * @author Jeremia
 */
public abstract class PIMWrapper extends ListItem {
    protected PIMItem pimItem;
    private boolean selected = false;
    
    public static final int NO_INT = Integer.MIN_VALUE;
    
    protected PIMWrapper() {}
    
    protected PIMWrapper( PIMItem pimItem ) {
        this.pimItem = pimItem;
    }
    
    protected PIMItem getPIMItem() {
        return pimItem;
    }
    
    protected String getString( int field ) {
        if( hasField( field ) )
            return pimItem.getString( field, 0 );
        else
            return "";
    }

    protected void setString( int field, String value ) {
        if( value == null || value.equals( "" ) ) {
            if( hasField( field ) )
                pimItem.removeValue( field, 0 );
        } else {
            if( hasField( field ) )
                pimItem.setString( field, 0, PIMItem.ATTR_NONE, value );
            else
                pimItem.addString( field, PIMItem.ATTR_NONE, value );
        }
    }
    
    protected String[] getStringArray( int field ) {
        if( hasField( field ) )
            return pimItem.getStringArray( field, 0 );
        else
            return null;
    }
    
    protected void setStringArray( int field, String[] value ) {
        if( value == null || value.length == 0 ) {
            if( hasField( field ) )
                pimItem.removeValue( field, 0 );
        } else {
            if( hasField( field ) )
                pimItem.setStringArray( field, 0, PIMItem.ATTR_NONE, value );
            else
                pimItem.addStringArray( field, PIMItem.ATTR_NONE, value );
        }
    }

    protected int getInt( int field ) {
        if( hasField( field ) )
            return pimItem.getInt( field, 0 );
        else
            return NO_INT;
    }

    protected void setInt( int field, int value ) {
        if( value == NO_INT ) {
            if( hasField( field ) )
                pimItem.removeValue( field, 0 );
        } else {
            if( hasField( field ) )
                pimItem.setInt( field, 0, PIMItem.ATTR_NONE, value );
            else
                pimItem.addInt( field, PIMItem.ATTR_NONE, value );
        }
    }

    protected Date getDate( int field ) {
        if( hasField( field ) )
            return new Date( pimItem.getDate( field, 0 ) );
        else
            return null;
    }

    protected void setDate( int field, Date value ) {
        if( value == null ) {
            if( hasField( field ) )
                pimItem.removeValue( field, 0 );
        } else {
            if( hasField( field ) )
                pimItem.setDate( field, 0, PIMItem.ATTR_NONE, value.getTime() );
            else
                pimItem.addDate( field, PIMItem.ATTR_NONE, value.getTime() );
        }
    }

    protected boolean getBoolean( int field ) {
        if( hasField( field ) )
            return pimItem.getBoolean( field, 0 );
        else
            return false;
    }

    protected void setBoolean( int field, boolean value ) {
        if( hasField( field ) )
            pimItem.setBoolean( field, 0, PIMItem.ATTR_NONE, value );
        else
            pimItem.addBoolean( field, PIMItem.ATTR_NONE, value );
    }
    
    public boolean equals( Object o ) {
        if( !(o instanceof PIMWrapper) ) {
            return false;
        }

        PIMWrapper itemToCompare = (PIMWrapper) o;
        return (pimItem.equals( itemToCompare.pimItem ));
    }

    public IClonable clone() {
        try {
            PIMWrapper clone = (PIMWrapper)getClass().newInstance();
            clone.pimItem = pimItem;
            return clone;
        } catch( Exception e ) {
            return null;
        }
    }
    
    public void setSelected( boolean selected ) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
    
    public boolean hasField( int field ) {
        int[] fields = pimItem.getFields();
        for( int i=0; i<fields.length; i++ ) {
            if( fields[i] == field )
                return true;
        }
        
        return false;
    }
}
