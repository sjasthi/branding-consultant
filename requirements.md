# AI-Assisted Branding Consultant for Educational GitHub Repositories

## Problem Statement

Learn and Help is a non-profit organization that maintains educational
content across multiple GitHub repositories. Over time, presentations,
documents, and course materials become inconsistent in branding,
including logos, fonts, headers, footers, colors, layouts, and writing
style.

The goal of this capstone project is to build an AI-powered system that
reviews repository content, recommends branding improvements, applies
approved changes automatically, and optionally commits updates back to
GitHub.

## Objectives

-   Analyze an entire GitHub repository
-   Detect supported file types (PowerPoint, Word, Markdown, HTML, PDF)
-   Review branding consistency
-   Generate AI-powered recommendations
-   Allow user approval
-   Apply approved changes automatically
-   Optionally commit changes back to GitHub

## Major Components

### Repository Scanner

-   Clone GitHub repository
-   Discover supported files
-   Build repository inventory

### AI Review Engine

Uses OpenAI to analyze: - Fonts - Colors - Logos - Headers and footers -
Layout consistency - Tone and writing style

Outputs: - Branding score - Detected issues - Recommended improvements

### Recommendation Engine

-   Before/after suggestions
-   Prioritized improvements
-   Estimated effort

### Approval Engine

-   Accept all recommendations
-   Accept selected recommendations
-   Reject recommendations

### Branding Fix Engine

Automatically updates: - PowerPoint presentations - Word documents -
Markdown files - HTML pages

### GitHub Integration (Stretch Goal)

-   Commit changes
-   Create Pull Requests

## Software Engineering Concepts Demonstrated

-   Requirements Engineering
-   Software Architecture
-   System Design
-   Python Development
-   API Integration
-   GitHub Integration
-   AI/LLM Integration
-   Prompt Engineering
-   Automated File Processing
-   Testing
-   Documentation
-   Agile Development

## FP5 -- Weekly Plan

  Week   Deliverable
  ------ -----------------------------------------------
  1      Finalize requirements and branding guidelines
  2      Design system architecture
  3      Build GitHub repository scanner
  4      Extract content from supported file types
  5      Integrate OpenAI API
  6      Generate branding recommendations
  7      Implement approval workflow
  8      Apply automated branding fixes
  9      Implement GitHub commit functionality
  10     System testing and documentation
  11     Final presentation preparation

## FP6 -- Repository Review

Review the Python 101 repository.

Deliverables: - Repository inventory - Branding analysis - Branding
guideline document - Gap analysis

Example Report:

``` text
Repository Summary

120 Markdown files
42 PowerPoint presentations
35 Images

Branding Issues
✓ Missing logo
✓ Mixed fonts
✓ Different title styles
✓ No footer
✓ Different color schemes
✓ Different slide layouts
```

## FP7 -- Repository Scanner

Deliverables: - Clone repository - Enumerate files - Categorize
content - Generate repository report

## FP8 -- AI Recommendation Engine

Deliverables: - Prompt engineering - OpenAI integration - Branding
recommendations - Review report

Example:

``` text
File: Week03.pptx

Recommendations
- Replace Arial with Aptos
- Add Learn and Help logo
- Use standard title bar
- Add footer
- Replace inconsistent icons
```

## FP9 -- Branding Automation Engine

Deliverables: - Update PowerPoint - Update Markdown - Update HTML -
Preserve formatting - Generate change log

## FP10 -- Integration and Testing

Deliverables: - End-to-end workflow - Multi-repository testing -
Performance testing - Error handling - User documentation

## Final Project Deliverables

-   Complete working application
-   Architecture document
-   Requirements specification
-   User manual
-   Demonstration using multiple repositories
-   Final report
-   Recorded demo

## Features That Strengthen the Capstone

-   Pluggable support for multiple LLM providers
-   Configurable branding rules using JSON or YAML
-   Approval workflow with audit trail
-   Unit, integration, and system tests
-   Branding compliance metrics
-   Modular architecture
-   Comprehensive logging and error handling
