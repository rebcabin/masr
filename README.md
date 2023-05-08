# masr
Meta ASR: replacement for aging ASDL

Pronounced like "MASER," Microwave Amplification of Stimulated Emission of
Radiation. Yes, it's a physics pun. We like physics puns.

# Run

- Install leiningen (look it up on the web, there is only one)

- type `lein test` at the terminal. Interacting with the tests is the
  best way, by far, to learn the code.

- type `lein run` at the terminal

# Read

`specs.md`

# Modify

- Write Clojure code and Markdown in `specs.clj` and `core_tests.clj`.

- Extract the Markdown file from the code via the following:

```bash
awk -f md4code.awk < ./src/masr/specs.clj > specs.md
```
