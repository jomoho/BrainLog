package org.hcilab.bcistub.core;

import org.eclipse.swt.widgets.Display;
import org.hcilab.bcistub.comm.CommunitactionHandler;
import org.hcilab.bcistub.core.EmotiveHandler.Target;
import org.hcilab.bcistub.gui.Mainframe;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.*;

public class ApplicationCore {

	public static void main(String[] args) 
    {   	
    	BackendControl ctrl = BackendControl.getInstance();
    	ctrl.startGUI();
    	
    	System.exit(0);
    }
}
