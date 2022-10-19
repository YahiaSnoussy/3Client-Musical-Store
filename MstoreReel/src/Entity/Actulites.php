<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Actulites
 *
 * @ORM\Table(name="actulites")
 * @ORM\Entity
 */
class Actulites
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_actu", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idActu;

    /**
     * @var int
     *
     * @ORM\Column(name="id_perso", type="integer", nullable=false)
     */
    private $idPerso = '0';

    /**
     * @var string
     *
     * @ORM\Column(name="body", type="text", length=65535, nullable=false)
     */
    private $body;

    /**
     * @var string
     *
     * @ORM\Column(name="categorie", type="string", length=150, nullable=false)
     */
    private $categorie;

    /**
     * @var string
     *
     * @ORM\Column(name="date", type="string", length=20, nullable=false)
     */
    private $date;


}
