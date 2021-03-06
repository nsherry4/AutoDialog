package net.sciencestudio.autodialog.view.swing.editors;

import java.awt.Dimension;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sciencestudio.autodialog.model.Parameter;

public class IntegerSliderEditor extends WrappingEditor<Integer, JSlider> {
	
	public IntegerSliderEditor() {
		this(new JSlider());
		component.setPreferredSize(new Dimension(150, 0));
		
	}
	
	public IntegerSliderEditor(JSlider component) {
		super(component);
	}

	
	@Override
	public void setEditorValue(Integer value){
		getComponent().setValue(value);
	}
	
	@Override
	public void initialize(Parameter<Integer> param) {
		
		this.param = param;

		setFromParameter();
		param.getValueHook().addListener(v -> this.setFromParameter());
		param.getEnabledHook().addListener(e -> setEnabled(e));
		
		getComponent().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				getEditorValueHook().updateListeners(getEditorValue());
				if (!param.setValue(getEditorValue())) {
					validateFailed();
				}
			}
		});				
	}
	
	@Override
	public Integer getEditorValue() {
		return getComponent().getValue();
	}
	
	
	@Override
	protected void setEnabled(boolean enabled) {
		component.setEnabled(enabled);
	}
}
