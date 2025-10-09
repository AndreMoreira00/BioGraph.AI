import pandas as pd
import requests 
import os

data_path = "./data/Rfam/Rfam.pdb"

column_names = [
    'rfam_acc', 
    'pdb_id', 
    'chain', 
    'pdb_start', 
    'pdb_end', 
    'bit_score', 
    'evalue_score', 
    'cm_start', 
    'cm_end', 
    'hex_colour'
]

data = pd.read_csv( 
  data_path, 
  sep=r'\s+',         
  header=1,      
  names=column_names, 
  comment='#'
)

def get_molecules_by_rfam(rfam_acc: str) -> dict:
    filtered = data[data['rfam_acc'] == rfam_acc]
    if filtered.empty:
        return {"error": "Família Rfam não encontrada."}
    
    result = filtered[['chain']]
    
    return result.to_dict(orient='records')


def get_data_by_rfam_and_chain(rfam_acc: str, chain: str) -> dict:
    filtered = data[(data['rfam_acc'] == rfam_acc) & (data['chain'] == chain)]
    if filtered.empty:
        return {"error": "Família Rfam não encontrada."}

    result = filtered[['rfam_acc', 'pdb_id', 'chain', 'pdb_start', 'pdb_end', 'bit_score', 'evalue_score', 'cm_start', 'cm_end', 'hex_colour']]

    result.sort_values(by=['bit_score', 'pdb_id'], ascending=[False, True], inplace=True)

    return result.to_dict(orient='records')[0]


# print(get_molecules_by_rfam('RF00001'))