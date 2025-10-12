import subprocess
import os
import tempfile

def Infernal(sequence: str) -> list:

  rfam_db_path = "./data/Cm/Rfam.cm" 
  fasta_input = f">user_sequence\n{sequence}\n"

  hits = []

  with tempfile.NamedTemporaryFile(mode='w+', delete=True, suffix=".txt") as tblout_file:

      command = [
          'cmscan',
          '--noali',
          '--tblout', tblout_file.name,
          rfam_db_path,
          '-'
      ]

      try:
          subprocess.run(
              command,
              input=fasta_input,
              capture_output=True,
              text=True,
              check=True
          )
      except FileNotFoundError:
          raise RuntimeError("Erro: O comando 'cmscan' não foi encontrado.")
      except subprocess.CalledProcessError as e:
          raise RuntimeError(f"O Infernal retornou um erro:\n{e.stderr}")

      tblout_file.seek(0)

      for line in tblout_file:
          if line.strip().startswith('#'):
              continue

          parts = line.split()
          
          if len(parts) < 18:
              continue
      
          hit_data = {
              'target_name': parts[0],
              'rfam_acc': parts[1],
              'e_value': float(parts[15]),
              'bit_score': float(parts[14]),
              'bias': float(parts[13])      
          }

          if hit_data['bit_score'] >= 60.0:
              hits.append(hit_data)
              
  hits.sort(key=lambda x: x['e_value'])
  return hits

# Teste

# user_sequence = "GCCUGGCGGCCGUAGCGCGGUGGUCCCACCUGACCCCAUGCCGAACUCAGAAGUGAAACGCCGUAGCGCCGAUGGUAGUGUGGGGUCUCCCCAUGCGAGAGUAGGGAACUGCCAGGCAU"
# try:
#   results = Infernal(user_sequence)
#   print(results)
# except Exception as e:
#   print("\nNenhuma família correspondente significativa foi encontrada.")