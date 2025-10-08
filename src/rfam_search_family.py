import pandas as pd

data_path = "src/data/Rfam/Rfam.pdb"

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
    
    result = filtered['chain']
    
    return result.to_dict(orient='records')

print(get_molecules_by_rfam('RF00001'))