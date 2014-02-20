package sentimentanalysis.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {

	/**
	 * @param args
	 */
	static TelaPF TPF;
	static InterpretaXMLModelo InXM;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* Colocar um while infinito*/
		Controlador cont = new Controlador();
		 TPF =  new TelaPF(cont);
		 TPF.setVisible(true);
		 if(args.length > 0)
			 InXM =  new InterpretaXMLModelo(args[0]);
		 else
			 InXM =  new InterpretaXMLModelo(null);
	}

		public void actionPerformed(ActionEvent event) {
			String action = event.getActionCommand();
			if (action.equals("Sair")) {
				System.exit(0);
			}else if (action.equals("OK")){
				InXM.Processa(TPF.getJTextFieldLogin(),TPF.getJCheckBoxInternet(),TPF.getJCheckBoxDeletar());
			}
		}

}
