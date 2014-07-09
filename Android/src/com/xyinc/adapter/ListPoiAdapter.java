package com.xyinc.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xyinc.R;
import com.xyinc.view.PontoReferenciaView;

/**
 * @author wellington.fernandes
 * Adapter para a Lista de Ponto de Referencia
 */
public class ListPoiAdapter extends BaseAdapter {

	private Context context;
	private List<PontoReferenciaView> listPoi;

	public ListPoiAdapter(Context context, List<PontoReferenciaView> listPoi) {
		this.context = context;
		this.listPoi = listPoi;
	}

	public int getCount() {
		return listPoi.size();
	}

	public Object getItem(int position) {
		return listPoi.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {
		
		PontoReferenciaView pontoReferencia = listPoi.get(position);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.poi_linha_tabela, null);
		
		TextView nomeReferencia = (TextView) view.findViewById(R.id.txtNome);
		nomeReferencia.setText(pontoReferencia.getNome().toString());

		TextView coordx = (TextView) view.findViewById(R.id.txtCoordenadax);
		coordx.setText(pontoReferencia.getCoordenadaX().toString());

		TextView coordy = (TextView) view.findViewById(R.id.txtCoordenaday);
		coordy.setText(pontoReferencia.getCoordenadaY().toString());
		
		if(pontoReferencia.getDistancia() == null){
			TextView txtDistancia = (TextView) view.findViewById(R.id.txtDistancia);
			txtDistancia.setText("");
		}else{
			TextView distancia = (TextView) view.findViewById(R.id.txtDistRef);
			distancia.setText(pontoReferencia.getDistancia().toString());
		}

		return view;
	}
}
