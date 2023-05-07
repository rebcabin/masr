# masr
Meta ASR: replacement for aging ASDL

Pronounced like "MASER," Microwave Amplification of Stimulated Emission of
Radiation. Yes, it's a physics pun. We like physics puns.

# Run

- Install leiningen (look it up on the web, there is only one)

- Extract code from markdown:

```bash
  awk -f code4md.awk < specs.md > ./src/masr/specs.clj
```

- type `lein test` at the terminal. Interacting with the tests is the
  best way, by far, to learn the code.

- type `lein run` at the terminal

# Read

The code is well commented. More formal documentation
awaits the time when we have more development.

# Modify

- `masr-summary.org`, `masr-summary.org`

- Write Clojure code in `specs.clj` and `core_tests.clj`
