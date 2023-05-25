# masr
Meta ASR: replacement for aging ASDL

Pronounced like "MASER," Microwave Amplification of Stimulated Emission of
Radiation. Yes, it's a physics pun. We like physics puns.

# Run

- Install leiningen (look it up on the web, there is only one).

- Type `lein test` at the terminal. Interacting with the tests is the
  best way, by far, to learn the code.

- Type `lein test :only masr.core-test/Module-test`, for example,
  to run just one of the tests.

- Type `lein run` at the terminal; doesn't do much, yet.

# Read

- `core_tests.clj`

- example inputs in the `resources/reference` subdirectory

- `specs.md`

  Looks best in the IntelliJ IDEs. Doesn't look so good in
  Visual-Studio Code. Any renderer of Markdown should be
  serviceable.

# Modify

- Write Clojure code and Markdown in `specs.clj` and `core_tests.clj`.

- Extract the Markdown file from the code as follows:

```bash
awk -f md4code.awk < ./src/masr/specs.clj > specs.md
```
