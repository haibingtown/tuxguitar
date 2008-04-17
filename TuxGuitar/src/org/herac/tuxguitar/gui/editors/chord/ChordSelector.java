/*
 * Created on 02-ene-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.herac.tuxguitar.gui.editors.chord;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.herac.tuxguitar.gui.TuxGuitar;
import org.herac.tuxguitar.gui.helper.SyncThread;
import org.herac.tuxguitar.gui.util.TGMusicKeyUtils;

/**
 * @author julian
public class ChordSelector extends Composite{
    
	public static final String[][] KEY_NAMES = new String[][]{
		TGMusicKeyUtils.getSharpKeyNames(TGMusicKeyUtils.PREFIX_CHORD),
		TGMusicKeyUtils.getFlatKeyNames(TGMusicKeyUtils.PREFIX_CHORD),
	};
	
	private ChordDialog dialog;
    private int[] tuning;
    private List tonicList;
    private List chordList;
    private List alterationList;
    private Button sharpButton;
    private Button flatButton;
    private Combo bassCombo;
    private Button addCheck;
    private List plusMinusList;
    private List _5List;
    private List _9List;
    private List _11List;

	public ChordSelector(ChordDialog dialog,Composite parent,int style,int[] tuning) {        
        super(parent,style);
        this.setLayout(new GridLayout(3,false));
        this.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
        this.dialog = dialog;
        this.tuning = tuning;
        this.init();
    }

    
    public void init(){    	
    	Composite tonicComposite = new Composite(this,SWT.NONE);
    	
    	this.tonicList = new List(tonicComposite,SWT.BORDER);
    	
        // sharp & flat buttons
        separator.setLayoutData(new GridData(SWT.FILL,SWT.BOTTOM,true,true));
        		if(new ChordSettingsDialog().open(ChordSelector.this.getShell())){
        			new SyncThread(new Runnable() {				
        				public void run() {
        					ChordSelector.this.showChord();
        					getChordList().redraw();
        				}
        			}).start();
        		}

        initChordWidgets();

        // fill the List widgets with text
        
        for(int i = 0 ; i < ChordDatabase.length(); i ++) {
        	this.chordList.add( ChordDatabase.get(i).getName() );
        }
        /*
        

        this.bassCombo.addSelectionListener(new SelectionAdapter() {
        this.chordList.addSelectionListener(new SelectionAdapter() {
                	adjustWidgetAvailability();
                	

        this.alterationList.addSelectionListener(new SelectionAdapter() {
        
	            	/*
	            	*/
        /*
        */
        this.adjustWidgetAvailability();
    
    //-======================================================    
    //-======================================================
    protected void initChordWidgets() {
    	Composite alterationComposite = new Composite(this,SWT.NONE);
    	Composite aboveComposite = new Composite(alterationComposite,SWT.NONE);
    	Composite firstComposite = new Composite(aboveComposite,SWT.NONE);
        this.alterationList = new List(firstComposite,SWT.BORDER);
        this.plusMinusList = new List(firstComposite,SWT.BORDER);

    	
    	Composite secondComposite = new Composite(aboveComposite,SWT.NONE);
        this._5List = new List(secondComposite,SWT.BORDER);

        this.addCheck = new Button(bassComposite, SWT.CHECK | SWT.LEFT);
        Label bText = new Label(bassComposite,SWT.LEFT);
        this.bassCombo = new Combo(bassComposite, SWT.DROP_DOWN | SWT.READ_ONLY);
    }
    
    protected void insertTonicNames(boolean sharp){
    	
    	String[] names = KEY_NAMES[ sharp?0:1 ];
    	
     // update the buttons
 	 this.flatButton.setSelection(!sharp);
 	 this.sharpButton.setSelection(sharp);
     // keep the old position
	 int indexL = this.tonicList.getSelectionIndex();
     if (indexL==-1) indexL=0;
	 int indexC = this.bassCombo.getSelectionIndex();
     if (indexC==-1) indexC=0;
     
     // update the list
     this.tonicList.removeAll();
     this.bassCombo.removeAll();
     for(int i = 0;i < names.length;i++){
         this.tonicList.add(names[i]);
         this.bassCombo.add(names[i]);
     }
     this.tonicList.setSelection(indexL);
     this.bassCombo.select(indexC);
    }

    
    //-======================================================    
    
    private String[] getPlusMinus(String text){
        String[] names = new String[3];
     
        names[0] = " ";
        names[1] = text+"+";
        names[2] = text+"-";
        
        return names;
    }  
    
    
    
    
    private String[] getAlterationNames(){
        String[] names = new String[4];
     
        names[0] = " ";
        names[1] = "9";
        names[2] = "11";
        names[3] = "13";
        
        return names;
    }  
    
    //-======================================================    
    
    protected void showChord(){
    	TuxGuitar.instance().loadCursor(getShell(),SWT.CURSOR_WAIT);
    	ChordCreatorListener listener = new ChordCreatorListener() {
    		public void notifyChords(final ChordCreatorUtil instance,final java.util.List chords) {
    			TuxGuitar.instance().getDisplay().syncExec(new Runnable() {
    				public void run() {
    					if(instance.isValidProcess() && !getDialog().isDisposed()){
    						getDialog().getList().setChords(chords);
							TuxGuitar.instance().loadCursor(getShell(),SWT.CURSOR_ARROW);
    					}
					}
				});
			}
		};

		ChordCreatorUtil.getChords(listener,
                                   this.tuning,
                                   this.chordList.getSelectionIndex(),
                                   this.alterationList.getSelectionIndex(),
                                   this.plusMinusList.getSelectionIndex(),
                                   this.addCheck.getSelection(),
                                   this._5List.getSelectionIndex(),
                                   this._9List.getSelectionIndex(),
                                   this._11List.getSelectionIndex(),
                                   this.bassCombo.getSelectionIndex(),
                                   this.tonicList.getSelectionIndex(),
                                   this.sharpButton.getSelection());
    */
    
    protected void updateWidget(List widget, boolean enabled) {
    	widget.setEnabled(enabled);
    	if(!enabled){
    		widget.setSelection(0);
    	}
    }
    
    protected void updateWidget(Button widget, boolean enabled) {
    	widget.setEnabled(enabled);
    	if(!enabled){
    		widget.setSelection(false);
    	}
    }        
    
    
    
    
    /**
     * Sets all the widgets' fields into recognized chord 
     * (tonic, bass, chord, alterations)
     */
    public void adjustWidgets(int tonic, int chordBasic, int alteration, int bass, int plusMinus, int addBoolean, int index5, int index9, int index11) {
    	this.setRefresh(false);
    	// adjust widgets
    	this.tonicList.setSelection(tonic);
    	this.alterationList.setSelection(alteration);
    	this.bassCombo.select(bass);
    	this.plusMinusList.setSelection(plusMinus);
    	this.addCheck.setSelection(addBoolean != 0);
    	this._5List.setSelection(index5);
    	this._9List.setSelection(index9);
    	this._11List.setSelection(index11);//this._9List.setSelection(index11);
    	this.chordList.setSelection(chordBasic);
    	this.adjustWidgetAvailability();
    	this.setRefresh(true);
    	// refresh manually
    	this.showChord();
    	//sel.redraw();
    }    
    
    
    
    	String chordName = ChordDatabase.get(getChordList().getSelectionIndex()).getName();
    	if (chordName.equals("dim") || chordName.equals("dim7") || chordName.equals("aug") || chordName.equals("5") ) {
    		updateWidget(getAlterationList(),false);
    		updateWidget(getAddCheck(),false);
    		updateWidget(get_9List(),false);
    		updateWidget(get_11List(),false);
    		updateWidget(getPlusMinusList(),false);
    		
    		if (!chordName.equals("5")){
    			updateWidget(get_5List(),false);//disableWidget(get_5List());
    		}else{
    			updateWidget(get_5List(),true);
    		}
    	}
    	else {
    		// enable and don't change the selection index
    		//getAlterationList().setEnabled(true);
    		//get_5List().setEnabled(true);
    		updateWidget(getAlterationList(),true);
    		updateWidget(get_5List(),true);
    	}    	
    	
    	if(this.alterationList.isEnabled()){
    		int currentIndex = this.alterationList.getSelectionIndex();
   			updateWidget(this._9List, (currentIndex >= 2 && !this.addCheck.getSelection() ) );
   			updateWidget(this._11List, (currentIndex >= 3 && !this.addCheck.getSelection() ) );
    	}
    protected ChordDialog getDialog() {
		return this.dialog;
	}

	protected List getTonicList() {
		return this.tonicList;
	}

	protected List getChordList() {
		return this.chordList;
	}

	protected List getAlterationList() {
		return this.alterationList;
	}

	protected Button getSharpButton() {
		return this.sharpButton;
	}

	protected Button getFlatButton() {
		return this.flatButton;
	}

	protected Combo getBassCombo() {
		return this.bassCombo;
	}

	protected Button getAddCheck() {
		return this.addCheck;
	}

	protected List getPlusMinusList() {
		return this.plusMinusList;
	}

	protected List get_5List() {
		return this._5List;
	}

	protected List get_9List() {
		return this._9List;
	}

	protected List get_11List() {
		return this._11List;
	}    
}
