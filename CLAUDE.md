# hoshimori 星守 — off-Earth / orbital (軌道) stewardship mirror

**ADR**: 2606073600 · **depends**: 2606073000 (inochi) + 2606073200 (asobi) + 2606073400
(hokorobi — sibling pattern) · 2605192330 (orbital land-sovereignty claim) · 2606041827
(watari — live ship/aircraft KG) · 2606012600 (watatsuna — cable chokepoints) · 2605312345
(Datom = canonical state) · 2605215000 (Murakumo-only). **Status**: 🟡 R0 design-only.

hoshimori ("星守" = guardian of the things in the sky) is the **orbital sibling** of the
live/infrastructure-resilience lineage (watari for ships/aircraft, watatsuna for submarine
cables). It mirrors **public orbital catalogs** — orbital regimes, operators/constellations,
hazards, and the public services that depend on orbit — into the kotoba Datom log, and
surfaces **orbital-congestion concentration** (which regimes bear the most crowding /
collision / debris risk) vs **stewardship** (remediation / deconfliction / disposal), routed
to **STEWARDSHIP** (orbital sustainability). It sits under the orbital land-sovereignty claim
(ADR-2605192330).

It closes coverage-gap **B** of ADR-2606073000.

## Hard gates (constitutional — read before any change)

- **G1 — STEWARDSHIP map, NEVER a targeting / interception aid.** This is the defining
  inversion, and it is load-bearing because orbital position data is **dual-use**. hoshimori
  mirrors **only already-public catalogs**; it emits **no precise predictive ephemeris** (no
  interception-grade state vector); all positional facts are **orbital-shell / regime-aggregate
  band labels**. ASAT / kinetic-intercept / collision-causing uses are **unrepresentable**
  (Charter §1.12 Transparent-Force: open + on-chain + 1 SBT = 1 vote). A dedicated test
  (`test_g1_no_precise_ephemeris`) asserts no per-object lat/lon/alt/velocity/TLE attribute.
- **G2 — edge-primary (N1).** Congestion lives ONLY on edges (`:en/orbit-load`). A regime's
  congestion-concentration = the **integral of its incident inbound hazard/occupancy 縁**
  (severity × disclosed regime weight), computed **on read** — never a stored per-object
  score. There is no `:hoshimori/threat-of-object`.
- **G3 — non-adjudicating (N3).** Orbital-regime definitions and named **public** debris
  EVENTS (e.g. FY-1C 2007, Cosmos-1408 2021) are DISCLOSED facts, never hoshimori verdicts.
- **G4 — public venue.** Open-source + on-chain + 1 SBT = 1 vote. Never a private/covert
  orbital registry.
- **G5 — sourcing honesty.** Every record `:authoritative | :representative`; orbit-load
  values are **representative severities, not measured conjunction probabilities**.
- **G6 — Murakumo-only narration** (ADR-2605215000).
- **G7 — outward-gated.** Live catalog ingest (space-track / CelesTrak-shaped public feeds)
  requires Council + operator DID. R0 = analyzer + schema + seed only.
- **G8 — observation-only.** hoshimori operates no spacecraft and conducts no maneuver; it
  observes the public orbital commons and routes to stewardship.

## Layout

```
com-etzhayyim-hoshimori/
├── CLAUDE.md                          # this file
├── manifest.edn                       # canonical actor manifest (3 cells, 8 gates)
├── schema/orbit-ontology.edn          # actor-owned canonical vocabulary
├── data/
│   └── seed-orbit-graph.kotoba.edn    # real PUBLIC regimes/operators/hazards/services + 縁
├── src/hoshimori/methods/             # portable Clojure/CLJS implementation
├── test/hoshimori/                    # behavior and contract tests
├── wasm/
│   └── README.md                      # kotoba pywasm actor (componentize-py) design
└── out/                               # GENERATED — do not hand-edit
    ├── congestion-report.md
    ├── orbit-datoms.kotoba.edn
    └── coverage-report.md
```

## Run

```bash
bb test
```

## Cross-links

hoshimori is the orbital member of the resilience-map family: **watari** (live ship/aircraft
positions → safety), **watatsuna** (submarine-cable chokepoints → redundancy), and now
hoshimori (orbital congestion → stewardship). All three are chokepoint/concentration mirrors
routed to resilience, never target-lists. The seed surfaces **LEO-low** as the top congestion
concentrator (megaconstellation + debris band) and **PNT-on-MEO** as a top service-dependency
fragility — both routed to deconfliction and active-debris-removal, never to harm.
## Standalone multirepo contract

- `manifest.edn`, repository metadata, schemas, and generated reports are canonical EDN.
  JSON is limited to the DID wire document under `.well-known/`.
- Generic publication invariants come from the SHA-pinned
  `com.etzhayyim/social-publication` dependency.
- IE-flow metrics, gates, and scoring come from the SHA-pinned
  `com.etzhayyim/ie-flow` dependency, which pins `com.etzhayyim/kotoba-datom` transitively.
- Source and tests use `src/hoshimori`, `test/hoshimori`, and repository-local data paths. Do not restore
  `20-actors`, `70-tools`, or superproject-relative classpaths.
- Run `bb test` from a standalone checkout before committing.
