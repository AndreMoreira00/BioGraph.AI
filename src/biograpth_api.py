from Bio.PDB import PDBParser, PDBIO

parser = PDBParser()

def get_structure_by_chain_pdb_id_start_end(chain_id, pdb_id, pdb_start, pdb_end):
  structure = parser.get_structure(f"{chain_id}", f"/home/andre/BioGraph.AI/src/data/pdbs/{pdb_id}.pdb")
  
  visualization_data = {
    "target_molecule": {
        "chain_id": chain_id,
        "residues": []
    },
    "context_molecules": []
  }
  
  for model in structure:
    for chain in model:
      if chain.id == chain_id:
        for residue in chain:
          res_id = residue.get_id()[1]
          res_name = residue.get_resname().strip()
          try:
            coords = residue["C1'"].get_coord().tolist()
          except KeyError:
            coords = residue["P"].get_coord().tolist() if "P" in residue else [0.0, 0.0, 0.0]

          visualization_data["target_molecule"]["residues"].append({
            "residue_name": res_name,
            "residue_pdb_num": res_id,
            "coordinates": coords[:2],
            "is_target_segment": pdb_start <= res_id <= pdb_end
          })
      else:
        context_molecule = {
            "chain_id": chain.id,
            "atoms": []
        }
        for atom in chain.get_atoms():
            context_molecule["atoms"].append({
                "element": atom.element,
                "coordinates": atom.get_coord().tolist()[:2],
            })
        visualization_data["context_molecules"].append(context_molecule)

  return visualization_data
  
# print(get_structure_by_chain_pdb_id_start_end('A', '2j28', 2, 117))